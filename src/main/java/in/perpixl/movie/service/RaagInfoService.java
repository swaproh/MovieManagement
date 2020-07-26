package in.perpixl.movie.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RaagInfoService {
	
	@Value("${raag.swaras:NoSwarasFound}")
	String swaras;
	
	@Value("${raag.thaatas:NoThaatasFound}")
	String thaatas;
	
	@Value("${raag.jaatis:NoJaatisFound}")
	String jaatis;
	
	@Value("${raag.samayas:NoSamayasFound}")
	String samayas;
	
	@Value("${raag.data.seperator:\\u0020}") // \u0020 is for space
	String raagDataSeperator;
	
	public List<String> getAllThaatas(){
		return Arrays.asList(thaatas.split(raagDataSeperator));
	}

	public List<String> getAllJatis() {
		return Arrays.asList(jaatis.split(raagDataSeperator));
	}

	public List<String> getAllSamay() {
		return Arrays.asList(samayas.split(raagDataSeperator));
	}
	
	public List<String> getAllSwaras() {
		return Arrays.asList(swaras.split(raagDataSeperator));
	}
}
