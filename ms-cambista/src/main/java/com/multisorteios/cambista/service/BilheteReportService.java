package com.multisorteios.cambista.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.multisorteios.cambista.trasnfer.BilheteReportTO;
import com.multisorteios.common.util.StringUtils;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRGraphics2DExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleGraphics2DExporterConfiguration;

@Service
public class BilheteReportService {

	@Value("${br.com.rifasoft.multisorteios.file.basedirectory}")
	private String baseDirectory;

	public String gerarRelatorio(BilheteReportTO bilhete, boolean cancelamento) {
		try {

			String resourceName = "reports/comprovante.jasper";
			String prefix = cancelamento ? "cancelamento_" : "comprovante_";

			// Caminho do arquivo .jasper
			InputStream reportStream = BilheteReportService.class.getClassLoader().getResourceAsStream(resourceName);

			// Carregar o relatório compilado (.jasper) diretamente
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

			// Dados dinâmicos para o relatório
			Map<String, Object> parametros = new HashMap<>();

			parametros.put("empresa", bilhete.getEmpresaNome());
			parametros.put("dataHora", bilhete.getDataHora());
			parametros.put("valorBilhete", StringUtils.applyMoneyMask(bilhete.getValor(), false));

			// Fonte de dados para tabelas dinâmicas
			List<Map<String, Object>> dataset = new ArrayList<>();
			for (String num : bilhete.getNumeros()) {
			    Map<String, Object> item = new HashMap<>();
			    item.put("numero", num); // Mapeia a String para o campo "numero"
			    dataset.add(item);
			}
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataset);

			// Preencher o relatório
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

			// Criação da imagem baseada no tamanho da página do relatório
			int width = jasperPrint.getPageWidth();
			int height = jasperPrint.getPageHeight();
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			// Cria o contexto Graphics2D da imagem
			Graphics2D graphics2D = bufferedImage.createGraphics();

			// Configura o exportador
			JRGraphics2DExporter exporter = new JRGraphics2DExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

			// Configuração do exportador
			SimpleGraphics2DExporterConfiguration configuration = new SimpleGraphics2DExporterConfiguration();
			exporter.setConfiguration(configuration);

			// Define a saída do exportador
			exporter.setExporterOutput(() -> graphics2D);

			// Exporta a primeira página do relatório para a imagem
			exporter.exportReport();

			// Salva a imagem como PNG
			//File outputFile = new File(baseDirectory + prefix + bilhete.getBilheteId() + ".png");
			File outputFile = new File("D:\\dev\\edenilton\\TMP\\" + prefix + bilhete.getBilheteId()  + ".png");
			ImageIO.write(bufferedImage, "png", outputFile);

			System.out.println("Relatório exportado como PNG: " + outputFile.getAbsolutePath());
			return outputFile.getName();


		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static void main(String[] args) {
		BilheteReportTO report = new BilheteReportTO();
		report.setBilheteId("13216354");
		report.setCambista("Raul");
		report.setDataHora("01/01/2025 10:15");
		report.setEmpresaNome("União +");
		report.setNome("Edenilton");
		report.setNumeros(Arrays.asList("01.02.03.04.05.06.07.08.09.00","11.12.13.14.15.16.17.18.19.10"));
		report.setQuantidade(2);
		report.setValor(new BigDecimal(20));
		
		new BilheteReportService().gerarRelatorio(report, false);
	}

}
