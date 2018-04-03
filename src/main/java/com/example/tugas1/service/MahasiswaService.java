package com.example.tugas1.service;

import java.util.List;
import java.util.Map;

import com.example.tugas1.model.MahasiswaModel;

public interface MahasiswaService extends DataService<MahasiswaModel> {
	MahasiswaModel selectMahasiswaByNPM(String npm);
	
	int countMahasiswaByTahunAndProdi(int tahun, int idProdi);
	
	int countMahasiswaByTahunAndProdiAndStatus(int tahun, int idProdi);
	
	List<MahasiswaModel> selectMahasiswaByProdi(int idProdi);
	
	Map<String, String> selectMahasiswaBy(int idUniv, int idFakultas, int idProdi);
}
