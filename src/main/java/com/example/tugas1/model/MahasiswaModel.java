package com.example.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MahasiswaModel {
	
	public static final String[] AGAMA_OPTIONS = {"Islam", "Budha", "Hindu", "Katolik", "Konghucu", "Protestan"};
	public static final String[] JENIS_KELAMIN_OPTIONS = {"Laki-laki", "Perempuan"};
	public static final String[] GOLONGAN_DARAH_OPTIONS = {"A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};
	public static final String[] JALUR_MASUK_OPTIONS = {"Ujian Tulis Bersama/SBMPTN", "Ujian Tulis Mandiri", "Undangan Olimpiade", "Undangan Paralel/PPKB", "Undangan Reguler/SNMPTN"};
	
	public static final int TAG_JK_PRIA = 1;
	public static final int TAG_JK_WANITA = 0;
	
	private int id;
	private String npm;
	private String nama;
	private String tempatLahir;
	private String tanggalLahir;
	private int jenisKelamin;
	private String agama;
	private String golonganDarah;
	private String status;
	private String tahunMasuk;
	private String jalurMasuk;
	private int idProdi;
	
	public static String generateNPM(String tahunMasuk, String kodeUniv, String kodeProdi, String jalurMasuk, String kodeInputData) {
		String kodeJalMsk = "";
		if (jalurMasuk.equals(JALUR_MASUK_OPTIONS[0])) {
			kodeJalMsk = "57";
		} else if (jalurMasuk.equals(JALUR_MASUK_OPTIONS[1])) {
			kodeJalMsk = "62";
		} else if (jalurMasuk.equals(JALUR_MASUK_OPTIONS[2])){
			kodeJalMsk = "53";
		} else if (jalurMasuk.equals(JALUR_MASUK_OPTIONS[3])) {
			kodeJalMsk = "55";
		} else if (jalurMasuk.equals(JALUR_MASUK_OPTIONS[4])) {
			kodeJalMsk = "54";
		}
		
		String kodeThnMsk = tahunMasuk.substring(2,4);
		return String.format("%s%s%s%s%s", kodeThnMsk, kodeUniv, kodeProdi, kodeJalMsk, kodeInputData);
	}
}
