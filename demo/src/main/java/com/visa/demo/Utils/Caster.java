package com.visa.demo.Utils;

import java.util.List;
import java.util.stream.Collectors;

import com.nojpa.bd.entity.Entity;

public class Caster<E> {
    
    @SuppressWarnings("unchecked")
    public  List<E> casteListe(List<Entity> list) {
        return list.stream().map(e -> (E) e).collect(Collectors.toList());
    }
}
