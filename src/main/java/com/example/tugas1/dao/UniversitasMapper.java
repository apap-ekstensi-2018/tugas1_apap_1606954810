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

import com.example.tugas1.model.UniversitasModel;

@Mapper
public interface UniversitasMapper {

	@Select("SELECT * FROM universitas WHERE id=#{id}")
	@Results({ @Result(property = "kodeUniversitas", column = "kode_univ"),
			@Result(property = "namaUniversitas", column = "nama_univ") })
	UniversitasModel selectUniversitas(@Param("id") int id);
	
	@Select("SELECT * FROM universitas WHERE nama_univ=#{name}")
	@Results({ @Result(property = "kodeUniversitas", column = "kode_univ"),
			@Result(property = "namaUniversitas", column = "nama_univ") })
	UniversitasModel selectUniversitasByName(@Param("name") String name);

	@Select("SELECT * FROM universitas WHERE #{value}")
	@Results({ @Result(property = "kodeUniversitas", column = "kode_univ"),
			@Result(property = "namaUniversitas", column = "nama_univ") })
	List<UniversitasModel> selectUniversitasByValue(@Param("value") String value);

	@Select("SELECT * FROM universitas")
	@Results({ @Result(property = "kodeUniversitas", column = "kode_univ"),
			@Result(property = "namaUniversitas", column = "nama_univ") })
	List<UniversitasModel> selectAllUniversitas();

	@Insert("INSERT INTO universitas VALUES (" + "#{kodeUniversitas}," + "#{namaUniversitas})")
	void insertUniversitas(UniversitasModel universitas);

	@Update("UPDATE universitas SET " + "kode_univ=#{kodeUniversitas}" + "nama_univ=#{namaUniversitas}")
	void updateUniversitas(UniversitasModel universitas);

	@Delete("DELETE FROM universitas WHERE id=#{id}")
	void deleteUniversitas(@Param("id") int id);
}
