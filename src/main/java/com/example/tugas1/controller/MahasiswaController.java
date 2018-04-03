package com.example.tugas1.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.FakultasModel;
import com.example.tugas1.model.MahasiswaModel;
import com.example.tugas1.model.ProgramStudiModel;
import com.example.tugas1.model.UniversitasModel;
import com.example.tugas1.service.FakultasServiceDatabase;
import com.example.tugas1.service.MahasiswaServiceDatabase;
import com.example.tugas1.service.ProgramStudiServiceDatabase;
import com.example.tugas1.service.UniversitasServiceDatabase;

@Controller
public class MahasiswaController {

	@Autowired
	private MahasiswaServiceDatabase mahasiswaDAO;
	@Autowired
	private ProgramStudiServiceDatabase prodiDAO;
	@Autowired
	private FakultasServiceDatabase fakultasDAO;
	@Autowired
	private UniversitasServiceDatabase universitasDAO;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/mahasiswa")
	public String viewData(Model model, @RequestParam(value = "npm") String npm) {
		// MahasiswaModel mhs = mahasiswaDAO.selectData(Integer.valueOf(npm));
		/*
		 * MahasiswaModel mhs = mahasiswaDAO.selectMahasiswaByNPM(npm); if (mhs == null)
		 * { System.out.println("LOL " + npm); return ""; } model.addAttribute("npm",
		 * mhs.getNpm());
		 */
		if (npm == null) {
			model.addAttribute("msgError", "NPM tidak boleh kosong!");
			return "error-page";
		} else if (!npm.matches("[0-9]+")) {
			model.addAttribute("msgError", String.format("Kombinasi NPM %s salah!", npm));
			return "error-page";
		}
		
		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswaByNPM(npm);
		if (mahasiswa == null) {
			model.addAttribute("msgError", String.format("Data dengan NPM %s tidak ditemukan!", npm));
			return "error-page";
		}
		
		ProgramStudiModel prodi = prodiDAO.selectDataById(mahasiswa != null ? mahasiswa.getIdProdi() : -1);
		FakultasModel fakultas = fakultasDAO.selectDataById(prodi != null ? prodi.getIdFakultas() : -1);
		UniversitasModel universitas = universitasDAO
				.selectDataById(fakultas != null ? fakultas.getIdUniversitas() : -1);
		model.addAttribute("mahasiswa", mahasiswa);
		model.addAttribute("prodi", prodi);
		model.addAttribute("fakultas", fakultas);
		model.addAttribute("universitas", universitas);
		return "view-mahasiswa";
	}

	@RequestMapping("/mahasiswa/tambah")
	public String addDataForm(Model model) {
		MahasiswaModel mModel = null;
		model.addAttribute("mahasiswa", new MahasiswaModel());
		model.addAttribute("options_jk", mModel.JENIS_KELAMIN_OPTIONS);
		model.addAttribute("options_agama", mModel.AGAMA_OPTIONS);
		model.addAttribute("options_goldar", mModel.GOLONGAN_DARAH_OPTIONS);
		model.addAttribute("options_jalur_masuk", mModel.JALUR_MASUK_OPTIONS);
		model.addAttribute("linkSubmit", "/mahasiswa/tambah/submit");
		model.addAttribute("hideAlert", true);
		return "form-add-mahasiswa";
	}

	@RequestMapping(value = "/mahasiswa/tambah/submit", method = RequestMethod.POST)
	public String addDataSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa) {
		mahasiswa.setNpm(getGenerateNPM(mahasiswa, "insert"));
		mahasiswa.setStatus("Aktif");
		mahasiswaDAO.insertData(mahasiswa);
		
		// String dateTmp = mahasiswa.getTanggalLahir().toString();
		/*mahasiswa.setStatus("Aktif");
		mahasiswa.setNpm("14045");
		mahasiswaDAO.insertData(mahasiswa);
		model.addAttribute("hideAlert", false);*/
		model.addAttribute("statMessage","Sukses!");
		model.addAttribute("message", String.format("Mahasiswa dengan NPM %s berhasil ditambahkan.", mahasiswa.getNpm()));
		return "response-page";
		//return "redirect:/mahasiswa/tambah";
		//return "submit-success";
	}

	@RequestMapping("/mahasiswa/ubah/{npm}")
	public String editDataForm(Model model, @PathVariable("npm") String npm) {
		MahasiswaModel mModel = null;
		MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswaByNPM(npm);
		model.addAttribute("mahasiswa", mahasiswa);
		model.addAttribute("options_jk", mModel.JENIS_KELAMIN_OPTIONS);
		model.addAttribute("options_agama", mModel.AGAMA_OPTIONS);
		model.addAttribute("options_goldar", mModel.GOLONGAN_DARAH_OPTIONS);
		model.addAttribute("options_jalur_masuk", mModel.JALUR_MASUK_OPTIONS);
		model.addAttribute("linkSubmit", "/mahasiswa/ubah/submit");
		System.out.println(mahasiswa.getId());
		return "form-update-mahasiswa";
	}

	@RequestMapping(value = "/mahasiswa/ubah/submit", method = RequestMethod.POST)
	public String editDataSubmit(Model model, @ModelAttribute MahasiswaModel mahasiswa) {
		mahasiswa.setNpm(getGenerateNPM(mahasiswa, "update"));
		mahasiswa.setStatus("Aktif");
		mahasiswaDAO.updateData(mahasiswa);
		return "redirect:/mahasiswa/ubah/" + mahasiswa.getNpm();
		//return "submit-success";
	}

	@RequestMapping("/kelulusan")
	public String viewPresentationGraduation(Model model, @RequestParam(value = "thn", required = false) String tahun,
			@RequestParam(value = "prodi", required = false) String idProdi) {
		model.addAttribute("closeOutput", true);
		ProgramStudiModel prodi = new ProgramStudiModel();
		FakultasModel fakultas = new FakultasModel();
		UniversitasModel univ = new UniversitasModel();
		int countMahasiswa = 0;
		
		if (tahun != null && idProdi != null) {
			int parseTahun = Integer.valueOf(tahun);
			int parseIdProdi = Integer.valueOf(idProdi);
			countMahasiswa = mahasiswaDAO.countMahasiswaByTahunAndProdi(parseTahun, parseIdProdi);
			
			prodi = prodiDAO.selectDataById(parseIdProdi);
			fakultas = fakultasDAO.selectDataById(prodi != null ? prodi.getIdFakultas() : null);
			univ = universitasDAO.selectDataById(fakultas != null ? fakultas.getIdUniversitas() : null);
			model.addAttribute("closeForm", true);
			model.addAttribute("closeOutput", false);
		}
		
		model.addAttribute("prodi", prodi);
		model.addAttribute("fakultas", fakultas);
		model.addAttribute("universitas", univ);
		model.addAttribute("totalPresentase", countMahasiswa);
		model.addAttribute("infoPresentase", String.format("%s dari 100 Mahasiswa Telas Lulus", countMahasiswa));
		return "view-presentation-graduation";
	}

	@RequestMapping("/mahasiswa/cari")
	public String viewDataByValue(Model model,
			@RequestParam(value = "univ", required = false) String idUniv,
			@RequestParam(value = "fakultas", required = false) String idFakultas,
			@RequestParam(value = "prodi", required = false) String idProdi) {
		
		List<UniversitasModel> listUniv = universitasDAO.selectAllData();
		List<FakultasModel> listFakul = fakultasDAO.selectAllData();
		List<ProgramStudiModel> listProdi = prodiDAO.selectAllData();
		String namaFakultasSelected = null;
		String namaProdiSelected = null;
		
		model.addAttribute("optionsUniv", listUniv);
		
		if (idUniv != null) {
			List<FakultasModel> tListFakul = new ArrayList<>();
			for (FakultasModel fm : listFakul) {
				if (fm.getIdUniversitas() == Integer.valueOf(idUniv))
					tListFakul.add(fm);
			}
			model.addAttribute("idUnivSelected", Integer.valueOf(idUniv));
			model.addAttribute("hideFakultas", false);
			model.addAttribute("optionsFakul", tListFakul);
			
			if (idFakultas != null) {
				List<ProgramStudiModel> tListProdi = new ArrayList<>();
				for (ProgramStudiModel psm : listProdi) {
					if (psm.getIdFakultas() == Integer.valueOf(idFakultas))
						tListProdi.add(psm);
				}
				model.addAttribute("idFakultasSelected", Integer.valueOf(idFakultas));
				model.addAttribute("hideProdi", false);
				model.addAttribute("optionsProdi", tListProdi);
				
				if (idProdi != null) {
					List<MahasiswaModel> listMahasiswa = mahasiswaDAO.selectMahasiswaByProdi(Integer.valueOf(idProdi));
					List<String[]> tmpData = new ArrayList<>();
					String namaFakultas = null;
					
					for(MahasiswaModel mhs : listMahasiswa) {
						String[] tmpS = new String[6];
						for (FakultasModel fk : listFakul) {
							if (fk.getId() == Integer.valueOf(idFakultas)) {
								namaFakultas = fk.getNamaFakultas();
								break;
							}
						}
						tmpS[0] = String.valueOf(mhs.getId());
						tmpS[1] = mhs.getNpm();
						tmpS[2] = mhs.getNama();
						tmpS[3] = namaFakultas;
						tmpS[4] = mhs.getTahunMasuk();
						tmpS[5] = mhs.getJalurMasuk();
						tmpData.add(tmpS);
					}
					
					for(FakultasModel fm : listFakul) {
						if(fm.getId() == Integer.valueOf(idFakultas)) {
							namaFakultasSelected = fm.getNamaFakultas();
							break;
						}
					}
					
					for(ProgramStudiModel psm : listProdi) {
						if(psm.getId() == Integer.valueOf(idProdi)) {
							namaProdiSelected = psm.getNamaProdi();
							break;
						}
					}
					
					model.addAttribute("msgFindMahasiswa", String.format("Lihat data Mahasiswa Program Studi %s Fakultas %s", namaProdiSelected, namaFakultasSelected));
					model.addAttribute("listMahasiswa", tmpData);
					
					return "view-all-mahasiswa";
				}
			}
		}
		return "find-mahasiswa";
	}
	
	private String getGenerateNPM(MahasiswaModel mahasiswa, String mode) {
		ProgramStudiModel psm = prodiDAO.selectDataById(mahasiswa.getIdProdi());
		FakultasModel fm = fakultasDAO.selectDataById(psm.getIdFakultas());
		UniversitasModel um = universitasDAO.selectDataById(fm.getIdUniversitas());
		String tmpNPM = mahasiswa.generateNPM(
				mahasiswa.getTahunMasuk(), 
				String.valueOf(um.getKodeUniversitas()),
				String.valueOf(mahasiswa.getIdProdi()), 
				mahasiswa.getJalurMasuk(), 
				mode.equals("insert") ? "001" : mahasiswa.getNpm().substring(9, 12));
		int i = 1;
		if (mode.equals("update"))
			return tmpNPM;
		while (mahasiswaDAO.selectMahasiswaByNPM(tmpNPM) != null) {
			tmpNPM = mahasiswa.generateNPM(
					mahasiswa.getTahunMasuk(), 
					String.valueOf(um.getKodeUniversitas()),
					String.valueOf(mahasiswa.getIdProdi()), 
					mahasiswa.getJalurMasuk(), 
					String.format("00%s", i++));
		}
		return tmpNPM;
	}
}
