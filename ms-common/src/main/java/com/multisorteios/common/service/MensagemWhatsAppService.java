package com.multisorteios.common.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.multisorteios.common.model.WhatsAppEmpresa;
import com.multisorteios.common.model.fake.MensagemZAPI;
import com.multisorteios.common.util.StringUtils;

@Service
public class MensagemWhatsAppService {

	@Autowired
	private WhatsAppEmpresaService whatsAppEmpresaService;

	public void create(Integer empresaId, String destinatario, String mensagem, String remoteFilePath,
			boolean forceEmpresa) {
		if (StringUtils.isNullOrEmpty(mensagem) || StringUtils.isNullOrEmpty(destinatario)
				|| StringUtils.isNullOrEmpty(remoteFilePath) || empresaId == null) {
			return;
		}

		WhatsAppEmpresa whatsAppEmpresa = whatsAppEmpresaService.findByEmpresaId(empresaId);
		if (whatsAppEmpresa == null) {
			if (forceEmpresa) {
				return;
			}
			create(0, destinatario, mensagem, remoteFilePath, true);
			return;

		}

		if (!StringUtils.hasAnyEmpty(whatsAppEmpresa.getClientTokenZapi(), whatsAppEmpresa.getInstanceTokenZapi(),
				whatsAppEmpresa.getIntanceIdZapi())) {
			// faz o envio pela API Z-API
			try {
				String endpointUrl = String.format("https://api.z-api.io/instances/%s/token/%s/send-image",
						whatsAppEmpresa.getIntanceIdZapi(), whatsAppEmpresa.getInstanceTokenZapi());

				URL url = new URL(endpointUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				// conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json");
				// conn.setRequestProperty("User-Agent", "Java client");
				conn.setRequestProperty("Client-Token", "" + whatsAppEmpresa.getClientTokenZapi());

				String input = new Gson().toJson(new MensagemZAPI(destinatario, remoteFilePath, mensagem));
				System.out.println("payload = " + input);

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

				InputStreamReader reader = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(reader);

				StringBuilder responseJson = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					responseJson.append(line);
				}
				conn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void create(Integer empresaId, String destinatario, String mensagem, boolean forceEmpresa) {

		if (StringUtils.isNullOrEmpty(mensagem) || StringUtils.isNullOrEmpty(destinatario) || empresaId == null) {
			return;
		}

		WhatsAppEmpresa whatsAppEmpresa = whatsAppEmpresaService.findByEmpresaId(empresaId);
		if (whatsAppEmpresa == null) {
			if (forceEmpresa) {
				return;
			}
			create(0, destinatario, mensagem, true);
			return;

		}

		if (!StringUtils.hasAnyEmpty(whatsAppEmpresa.getClientTokenZapi(), whatsAppEmpresa.getInstanceTokenZapi(),
				whatsAppEmpresa.getIntanceIdZapi())) {
			// faz o envio pela API Z-API
			try {
				String endpointUrl = String.format("https://api.z-api.io/instances/%s/token/%s/send-text",
						whatsAppEmpresa.getIntanceIdZapi(), whatsAppEmpresa.getInstanceTokenZapi());

				URL url = new URL(endpointUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				// conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json");
				// conn.setRequestProperty("User-Agent", "Java client");
				conn.setRequestProperty("Client-Token", "" + whatsAppEmpresa.getClientTokenZapi());

				String input = new Gson().toJson(new MensagemZAPI(destinatario, mensagem));

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

				InputStreamReader reader = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(reader);

				StringBuilder responseJson = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					responseJson.append(line);
				}
				conn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
