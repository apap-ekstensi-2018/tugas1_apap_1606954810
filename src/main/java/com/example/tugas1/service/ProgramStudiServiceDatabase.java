package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.ProgramStudiMapper;
import com.example.tugas1.model.ProgramStudiModel;

@Service
public class ProgramStudiServiceDatabase implements ProgramStudiService {
	
	@Autowired
	private ProgramStudiMapper prodiMapper;

	@Override
	public ProgramStudiModel selectDataById(Object id) {
		return prodiMapper.selectProgramStudi((int) id);
	}

	@Override
	public List<ProgramStudiModel> selectAllData() {
		// TODO Auto-generated method stub
		return prodiMapper.selectAllProgramStudi();
	}

	@Override
	public void insertData(ProgramStudiModel data) {
		prodiMapper.insertProgramStudi(data);
	}

	@Override
	public void updateData(ProgramStudiModel data) {
		prodiMapper.updateProgramStudi(data);
	}

	@Override
	public void deleteData(Object id) {
		prodiMapper.deleteProgramStudi((int) id); 
	}
	
	
}
