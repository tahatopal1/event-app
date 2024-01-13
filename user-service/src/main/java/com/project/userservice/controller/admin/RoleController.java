package com.project.userservice.controller.admin;

import com.project.userservice.dto.RoleDTO;
import com.project.userservice.facade.admin.RoleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/roles")
public class RoleController {

    private final RoleFacade roleFacade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDTO> getAllRoles(){
        return roleFacade.findAllRoles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDTO getRole(@PathVariable("id") Long id){
        return roleFacade.findRole(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO saveRole(@RequestBody RoleDTO roleDTO){
        return roleFacade.saveRole(roleDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoleDTO updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO){
        return roleFacade.updateRole(id, roleDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@PathVariable("id") Long id){
        roleFacade.deleteRole(id);
    }

}
