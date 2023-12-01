package com.example.Enseignant.service;

import com.example.Enseignant.Dto.EnseignantResponse;
import com.example.Enseignant.entity.Enseignant;
import com.example.Enseignant.repository.EnseignantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnseignantService {
    private final EnseignantRepo enseignantRepo;
    public EnseignantResponse createEnseignant(Enseignant enseignant) {
        Optional<Enseignant> existingEnseignant = enseignantRepo.findById(enseignant.getId());
        if (existingEnseignant.isPresent()) {
            // Renvoyer l'enseignant existant
            return new EnseignantResponse(existingEnseignant.get(), true);
        } else {
            Enseignant newEnseignant = enseignantRepo.save(enseignant);
            // Renvoyer le nouvel enseignant ajouté
            return new EnseignantResponse(newEnseignant, false);
        }
    }
    public boolean updateEnseignant(Enseignant updatedEnseignant) {
        Long id = updatedEnseignant.getId();
        Optional<Enseignant> optionalEnseignant = enseignantRepo.findById(id);

        if (optionalEnseignant.isPresent()) {
            Enseignant existingEnseignant = optionalEnseignant.get();
            existingEnseignant.setCin(updatedEnseignant.getCin());
            existingEnseignant.setNom(updatedEnseignant.getNom());
            existingEnseignant.setPrenom(updatedEnseignant.getPrenom());
            existingEnseignant.setMail(updatedEnseignant.getMail());
            existingEnseignant.setGrade(updatedEnseignant.getGrade());
            enseignantRepo.save(existingEnseignant); // Sauvegarder les modifications
            return true; // Mise à jour réussie
        }

        return false; // Échec de la mise à jour, enseignant non trouvé
    }
    public void deleteEnseignantById(Long id) {
        enseignantRepo.deleteById(id);
    }
    public Optional<Enseignant> getEnseignantById(Long id) {

        return enseignantRepo.findById(id);
    }
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepo.findAll();
    }

}
