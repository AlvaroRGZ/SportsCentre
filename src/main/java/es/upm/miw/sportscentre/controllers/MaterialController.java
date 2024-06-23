package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.models.daos.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaterialController {

  @Autowired
  private MaterialRepository materialRepository ;

  public List<Material> findAll() {
    return materialRepository.findAll();
  }

  public Material findById(String id) {
    return materialRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Material not found"));
  }

  public List<Material> findByListOfIds(List<String> materialIds) {
    if (materialIds.isEmpty()) {
      return List.of();
    }
    return materialIds.stream().map(this::findById).toList();
  }

  public Material save(Material material) {
    return materialRepository.save(material);
  }

  public void deleteById(String id) {
    materialRepository.deleteById(id);
  }

  public void deleteAll() {
    materialRepository.deleteAll();
  }

  public Material update(String id, Material material) {
    Material existingMaterial = materialRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    existingMaterial.setName(material.getName());
    existingMaterial.setDescription(material.getDescription());
    existingMaterial.setQuantity(material.getQuantity());
    return materialRepository.save(existingMaterial);
  }
}