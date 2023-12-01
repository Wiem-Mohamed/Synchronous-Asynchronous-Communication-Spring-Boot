package com.example.Enseignant.controller;

import com.example.Enseignant.Dto.EnseignantResponse;
import com.example.Enseignant.entity.Enseignant;
import com.example.Enseignant.service.EnseignantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/enseignants")
@RequiredArgsConstructor
public class EnseignantController {
    private final EnseignantService enseignantService;
    @PostMapping("/add")
    public ResponseEntity<EnseignantResponse> ajouterEnseignant(@RequestBody Enseignant enseignant) {
        EnseignantResponse enseignantResponse = enseignantService.createEnseignant(enseignant);
        HttpStatus status = enseignantResponse.isExisting() ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(enseignantResponse, status);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnseignantById(@PathVariable Long id) {
        enseignantService.deleteEnseignantById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        Optional<Enseignant> enseignant = enseignantService.getEnseignantById(id);
        return enseignant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Enseignant>> getAllEnseignants() {
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        return new ResponseEntity<>(enseignants, HttpStatus.OK);
    }
    @PutMapping("/modifier")
    public ResponseEntity<String> modifierEnseignant(@RequestBody Enseignant updatedEnseignant) {
        boolean updateSuccess = enseignantService.updateEnseignant(updatedEnseignant);

        if (updateSuccess) {
            return ResponseEntity.ok("Mise à jour réussie !");
        } else {
            return ResponseEntity.badRequest().body("Impossible de mettre à jour l'enseignant. Enseignant non trouvé.");
        }
    }
}
