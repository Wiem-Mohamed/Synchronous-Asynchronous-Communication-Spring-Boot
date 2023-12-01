package com.example.Enseignant.Dto;

import com.example.Enseignant.entity.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnseignantResponse {
    private Enseignant enseignant;
    private boolean isExisting;
}
