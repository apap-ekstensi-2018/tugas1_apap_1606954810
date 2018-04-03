package com.example.tugas1.service;

import com.example.tugas1.model.UniversitasModel;

public interface UniversitasService extends DataService<UniversitasModel> {
	UniversitasModel selectUniversitasByName(String name);
}
