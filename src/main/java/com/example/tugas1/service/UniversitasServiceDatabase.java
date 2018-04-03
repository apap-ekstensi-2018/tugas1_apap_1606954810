package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.UniversitasMapper;
import com.example.tugas1.model.UniversitasModel;

@Service
public class UniversitasServiceDatabase implements UniversitasService {

	@Autowired
	private UniversitasMapper universitasMapper;

	@Override
	public UniversitasModel selectDataById(Object id) {
		return universitasMapper.selectUniversitas((int) id);
	}

	@Override
	public UniversitasModel selectUniversitasByName(String name) {
		return universitasMapper.selectUniversitasByName(name);
	}

	@Override
	public List<UniversitasModel> selectAllData() {
		return universitasMapper.selectAllUniversitas();
	}

	@Override
	public void insertData(UniversitasModel data) {
		universitasMapper.insertUniversitas(data);
	}

	@Override
	public void updateData(UniversitasModel data) {
		universitasMapper.updateUniversitas(data);
	}

	@Override
	public void deleteData(Object id) {
		universitasMapper.deleteUniversitas((int) id);
	}
}
