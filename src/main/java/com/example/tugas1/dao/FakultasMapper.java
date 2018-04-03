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

import com.example.tugas1.model.FakultasModel;

@Mapper
public interface FakultasMapper {

	@Select("SELECT * FROM fakultas WHERE id=#{id}")
	@Results({ @Result(property = "kodeFakultas", column = "kode_fakultas"),
			@Result(property = "namaFakultas", column = "nama_fakultas"),
			@Result(property = "idUniversitas", column = "id_univ") })
	FakultasModel selectFakultas(@Param("id") int id);

	@Select("SELECT * FROM fakultas WHERE #{value}")
	@Results({ @Result(property = "kodeFakultas", column = "kode_fakultas"),
			@Result(property = "namaFakultas", column = "nama_fakultas"),
			@Result(property = "idUniversitas", column = "id_univ") })
	List<FakultasModel> selectFakultasByValue(@Param("value") String value);

	@Select("SELECT * FROM fakultas")
	@Results({ @Result(property = "kodeFakultas", column = "kode_fakultas"),
			@Result(property = "namaFakultas", column = "nama_fakultas"),
			@Result(property = "idUniversitas", column = "id_univ") })
	List<FakultasModel> selectAllFakultas();

	@Insert("INSERT fakultas VALUES (" + "#{kodeFakultas}," + "#{namaFakultas}," + "#{idUniversitas})")
	void insertFakultas(FakultasModel fakultas);

	@Update("UPDATE fakultas SET " + "kode_fakultas=#{kodeFakultas}," + "nama_fakultas=#{namaFakultas},"
			+ "id_univ=#{idUniversitas}")
	void updateFakultas(FakultasModel fakultas);

	@Delete("DELETE FROM fakultas WHERE id=#{id}")
	void deleteFakultas(@Param("id") int id);
}
