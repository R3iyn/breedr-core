package eu.breedr.breedrcore.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapUtils {

    private ModelMapper modelMapper = new ModelMapper();

    public <S, T> T mapObject(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(s -> modelMapper.map(s, targetClass))
                .collect(Collectors.toList());
    }
}
