package es.upm.miw.sportscentre.views;

import es.upm.miw.sportscentre.controllers.MaterialController;
import es.upm.miw.sportscentre.models.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialView {
  private MaterialController materialController;

  MaterialView(@Autowired MaterialController materialController) {
    this.materialController = materialController;
  }

  @GetMapping
  public List<Material> getAll() {
    return materialController.findAll();
  }

  @GetMapping("/{id}")
  public Material getMaterialById(@PathVariable String id) {
    return materialController.findById(id);
  }

  @PostMapping
  public Material createMaterial(@RequestBody Material Material) {
    return materialController.save(Material);
  }

  @PutMapping("/{id}")
  public Material updateInstallation(@PathVariable String id, @RequestBody Material material) {
    return materialController.update(id, material);
  }

  @DeleteMapping("/{id}")
  public void deleteMaterial(@PathVariable String id) {
    materialController.deleteById(id);
  }

  @DeleteMapping
  public void deleteAllMaterials() {
    materialController.deleteAll();
  }
}