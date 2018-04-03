package com.example.tugas1.dao.experimental;

import org.apache.ibatis.jdbc.SQL;

public enum TableValues {
	MAHASISWA		(),
	PROGRAM_STUDI	(),
	UNIVERSITAS		(),
	FAKULTAS		();
	
	/*private static final String TAG_MAHASISWA = "mahasiswa";
	private static final String TAG_PROGRAM_STUDI = "program_studi";
	private static final String TAG_UNIVERSITAS = "universitas";
	private static final String TAG_FAKULTAS = "fakultas";*/
	public static class ValuesGenerator {
		public String hello() {
			return new SQL() {
				{
					SELECT("");
				}
			}.toString();
		}
		
		
	}
}
