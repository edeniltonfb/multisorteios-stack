package com.multisorteios.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionsUtils {

	public static boolean isNullOrEmpty(Collection<?> list){
		return list == null || list.isEmpty();
	}

	public static boolean isNullOrEmpty(Object[] list) {
		return (list == null) || (list.length == 0);
	}

	public static <T extends Comparable<? super T>> void sort(List<T> list) {
		Collections.sort(list);
	}
	
	/**
	 * Método que faz o mesmo que list.subList. A razão para essa implementação
	 * é que o subList não devolve uma list serializável.
	 * 
	 * @param list
	 * @param fromIndex
	 * @param toIndex
	 * @return
	 */
	public static <T> List<T> subList(List<T> list, int fromIndex, int toIndex) {
		final int size = list.size();
		ArrayList<T> newList = new ArrayList<T>(size);
		for (int x = fromIndex; x < toIndex; x++) {
			T o = list.get(x);
			newList.add(o);
		}
		return newList;
	}
	
	/**
	 * Procura uma string começando com um dado prefixo em alguma das listas passadas
	 * @param prefix
	 * @param lists
	 * @return
	 */
	public static boolean hasElementStartingWith(String prefix, Collection<?>... lists) {
		for (Collection<?> list : lists) {
			for (Object o : list) {
				if (o.toString().startsWith(prefix)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	// ======================== INSTRUCOES FUNCIONAIS
	
	/**
	 * Função MAP (LISP)
	 * @param list
	 * @param fun
	 * @return
	 */
	public static <T, R> List<R> map(MapFunction<T, R> fun, Collection<T> list) {
		List<R> result = new ArrayList<>(list.size());
		int x = 0;
		for (T o : list) {
			R ret = fun.visit(x, o);
			result.add(ret);
			x++;
		}
		return result;
	}
	
	public static interface MapFunction<T, R> {
		public R visit(int index, T object); 
	}

	public static Integer[] parseListToArray(List<Integer> objectList) {
		if(isNullOrEmpty(objectList)){
			return new Integer[] {};
		}
		int arraySize = objectList.size();
		Integer[] array = new Integer[arraySize];
		for(int index = 0; index < arraySize; index++){
			array[index] = objectList.get(index);
		}
		
		return array;
	}
	
	public static String[] parseStringListToArray(List<String> stringList) {
		if(isNullOrEmpty(stringList)){
			return new String[] {};
		}
		int arraySize = stringList.size();
		String[] array = new String[arraySize];
		for(int index = 0; index < arraySize; index++){
			array[index] = stringList.get(index);
		}
		
		return array;
	}
	
	public static <T> ArrayList<T> notNullValue(ArrayList<T> list) {
	    if (list == null) {
	        return new ArrayList<>();
	    }
	    return list;
	}

	public static int count(List<?> list) {
		if(list == null){
			return 0;
		}
		
		return list.size();
	}
	/** 
	 * Método que avalia a igualdade de duas coleções em função
	 * das diferenças entre os seus itens
	 * 	 
	 * @param col1
	 * @param col2
	 * @return
	 */
	public static <T> boolean equals(Collection<T> col1, Collection<T> col2){
	   if(col1==null || col2==null ){
		 return false;
	   }
	
       List<T> buffer = new ArrayList<T>(col1);
       buffer.removeAll(col2);
       return buffer.isEmpty() && col1.size() == col2.size();
	} 
	
	
}
