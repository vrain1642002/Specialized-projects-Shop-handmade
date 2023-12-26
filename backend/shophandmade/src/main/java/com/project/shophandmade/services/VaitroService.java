package com.project.shophandmade.services;


import com.project.shophandmade.models.Vaitro;
import com.project.shophandmade.repositories.VaitroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaitroService implements IVaitroService {
    private final VaitroRepository vaitroRepository;


    @Override
    public List<Vaitro> layVaitros() {
        return vaitroRepository.findAll();
    }
}
