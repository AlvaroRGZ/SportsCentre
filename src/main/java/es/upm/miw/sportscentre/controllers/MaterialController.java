package es.upm.miw.sportscentre.controllers;

import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.models.Material;
import es.upm.miw.sportscentre.models.daos.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialController {

  @Autowired
  private MaterialRepository materialRepository ;

  public List<Material> findAll() {
    return materialRepository.findAll();
  }

  public Material findById(String id) {
    return materialRepository.findById(id).orElse(null);
  }

  public List<Material> findByListOfIds(List<String> materialIds) {
    if (materialIds.size() == 0) {
      return List.of();
    }
    return materialIds.stream().map(this::findById).toList();
  }

  public Material save(Material Material) {
    return materialRepository.save(Material);
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