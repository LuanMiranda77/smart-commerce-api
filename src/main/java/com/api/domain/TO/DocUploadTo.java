package com.api.domain.TO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.api.domain.Estabelecimento;

import lombok.Data;

@Data
public class DocUploadTo {
	Estabelecimento estabelecimento;
	List<MultipartFile> files = new ArrayList<>();
}
