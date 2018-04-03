package com.example.tugas1.dao.experimental;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.FakultasMapper;
import com.example.tugas1.dao.MahasiswaMapper;
import com.example.tugas1.dao.ProgramStudiMapper;
import com.example.tugas1.dao.UniversitasMapper;
import com.example.tugas1.model.FakultasModel;
import com.example.tugas1.model.MahasiswaModel;
import com.example.tugas1.model.ProgramStudiModel;
import com.example.tugas1.model.UniversitasModel;
import com.example.tugas1.service.DataService;

@Service
public class DataServiceDatabase<T> implements DataService<T> {

	private T t;

	@Autowired
	private FakultasMapper fakultasMapper;
	@Autowired
	private MahasiswaMapper mahasiswaMapper;
	private ProgramStudiMapper prodiMapper;
	private UniversitasMapper univMapper;

	@Override
	@SuppressWarnings("unchecked")
	public T selectDataById(Object id) {
		System.out.println(id);
		if (t instanceof FakultasMapper)
			return (T) fakultasMapper.selectFakultas((int) id);
		if (t instanceof MahasiswaMapper) {
			System.out.println("nomor = " + id);
			//return (T) mahasiswaMapper.selectMahasiswa((int) id);
		}
		if (t instanceof ProgramStudiMapper)
			return (T) prodiMapper.selectProgramStudi((int) id);
		if (t instanceof UniversitasMapper)
			return (T) univMapper.selectUniversitas((int) id);
		return null;
	}

	/*@Override
	@SuppressWarnings("unchecked")
	public List<T> selectDataByValue(String value) {
		T t = (T) new Object();
		if (t instanceof FakultasMapper)
			return (List<T>) fakultasMapper.selectFakultasByValue(value);
		if (t instanceof MahasiswaMapper)
			return (List<T>) mahasiswaMapper.selectMahasiswaByNPM(value);
		if (t instanceof ProgramStudiMapper)
			return (List<T>) prodiMapper.selectProgramStudiByValue(value);
		if (t instanceof UniversitasMapper)
			return (List<T>) univMapper.selectUniversitasByValue(value);
		return null;
	}*/
	
	

	@Override
	@SuppressWarnings("unchecked")
	public List<T> selectAllData() {
		T t = (T) new Object();
		if (t instanceof FakultasMapper)
			return (List<T>) fakultasMapper.selectAllFakultas();
		if (t instanceof MahasiswaMapper)
			return (List<T>) mahasiswaMapper.selectAllMahasiswa();
		if (t instanceof ProgramStudiMapper)
			return (List<T>) prodiMapper.selectAllProgramStudi();
		if (t instanceof UniversitasMapper)
			return (List<T>) univMapper.selectAllUniversitas();
		return null;
	}

	/*@Override
	public MahasiswaModel selectMahasiswaByNPM(String npm) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	@SuppressWarnings("unchecked")
	public void insertData(T data) {
		T t = (T) new Object();
		if (t instanceof FakultasMapper)
			fakultasMapper.insertFakultas((FakultasModel) data);
		if (t instanceof MahasiswaMapper)
			mahasiswaMapper.insertMahasiwa((MahasiswaModel) data);
		if (t instanceof ProgramStudiMapper)
			prodiMapper.insertProgramStudi((ProgramStudiModel) data);
		if (t instanceof UniversitasMapper)
			univMapper.insertUniversitas((UniversitasModel) data);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void updateData(T data) {
		// TODO Auto-generated method stub
		T t = (T) new Object();
		if (t instanceof FakultasMapper)
			fakultasMapper.updateFakultas((FakultasModel) data);
		if (t instanceof MahasiswaMapper)
			mahasiswaMapper.updateMahasiswa((MahasiswaModel) data);
		if (t instanceof ProgramStudiModel)
			prodiMapper.updateProgramStudi((ProgramStudiModel) data);
		if (t instanceof UniversitasModel)
			univMapper.updateUniversitas((UniversitasModel) data);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteData(Object id) {
		// TODO Auto-generated method stub
		T t = (T) new Object();
		if (t instanceof FakultasMapper)
			fakultasMapper.deleteFakultas((int) id);
		if (t instanceof MahasiswaMapper)
			mahasiswaMapper.deleteMahasiswa((int) id);
		if (t instanceof ProgramStudiMapper)
			prodiMapper.deleteProgramStudi((int) id);
		if (t instanceof UniversitasMapper)
			univMapper.deleteUniversitas((int) id);
	}
}
