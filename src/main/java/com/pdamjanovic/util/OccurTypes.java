package com.pdamjanovic.util;

import org.apache.lucene.search.BooleanClause.Occur;

public class OccurTypes {
	
	public static Occur getOccur(String value){
		if(value.equals("+")){
			return Occur.MUST;
		}else if(value.equals("-")){
			return Occur.MUST_NOT;
		}else{
			return Occur.SHOULD;
		}
	}
}
