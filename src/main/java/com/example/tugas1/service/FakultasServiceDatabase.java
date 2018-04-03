package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.FakultasMapper;
import com.example.tugas1.model.FakultasModel;

@Service
public class FakultasServiceDatabase implements FakultasService {

	@Autowired
	private FakultasMapper fakultasMapper;

	@Override
	public FakultasModel selectDataById(Object id) {
		return fakultasMapper.selectFakultas((int) id);
	}

	@Override
	public List<FakultasModel> selectAllData() {
		return fakultasMapper.selectAllFakultas();
	}

	@Override
	public void insertData(FakultasModel data) {
		fakultasMapper.insertFakultas(data);
	}

	@Override
	public void updateData(FakultasModel data) {
		fakultasMapper.updateFakultas(data);
	}

	@Override
	public void deleteData(Object id) {
		fakultasMapper.deleteFakultas((int) id);
	}
}
