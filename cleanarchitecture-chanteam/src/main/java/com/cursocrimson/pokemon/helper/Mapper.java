package com.cursocrimson.pokemon.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

	@Autowired
	private ModelMapper modelMapper;

	public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream()
				.map(element -> modelMapper.map(element, targetClass))
				.collect(Collectors.toList());
	}

	public <S, T> T map(S source, Class<T> targetClass) {
		return source == null ? null : modelMapper.map(source, targetClass);
	}

}
