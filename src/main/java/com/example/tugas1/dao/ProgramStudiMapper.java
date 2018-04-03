package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.ProgramStudiModel;

@Mapper
public interface ProgramStudiMapper {

	@Select("SELECT * FROM program_studi WHERE id=#{id}")
	@Results({ @Result(property = "kodeProdi", column = "kode_prodi"),
			@Result(property = "namaProdi", column = "nama_prodi"),
			@Result(property = "idFakultas", column = "id_fakultas") })
	ProgramStudiModel selectProgramStudi(@Param("id") int id);

	@Select("SELECT * FROM program_studi WHERE #{value}")
	@Results({ @Result(property = "kodeProdi", column = "kode_prodi"),
			@Result(property = "namaProdi", column = "nama_prodi"),
			@Result(property = "idFakultas", column = "id_fakultas") })
	List<ProgramStudiModel> selectProgramStudiByValue(@Param("value") String value);

	@Select("SELECT * FROM program_studi")
	@Results({ @Result(property = "kodeProdi", column = "kode_prodi"),
			@Result(property = "namaProdi", column = "nama_prodi"),
			@Result(property = "idFakultas", column = "id_fakultas") })
	List<ProgramStudiModel> selectAllProgramStudi();

	@Insert("INSERT INTO program_studi VALUES ("
			+ "#{kodeProdi},"
			+ "#{namaProdi},"
			+ "#{idFakultas})")
	void insertProgramStudi(ProgramStudiModel programStudi);

	@Update("UPDATE program_studi SET "
			+ "kode_prodi=#{kodeProdi},"
			+ "nama_prodi=#{namaProdi},"
			+ "id_fakultas=#{idFakultas}")
	void updateProgramStudi(ProgramStudiModel programStudi);

	@Delete("DELETE FROM program_studi WHERE id=#{id}")
	void deleteProgramStudi(@Param("id") int id);
}
