package br.com.clientescrud.controller;

import br.com.clientescrud.controller.dto.UsuarioRequestDto;
import br.com.clientescrud.controller.dto.UsuarioRequestUpdateDto;
import br.com.clientescrud.controller.dto.UsuarioResponseDto;
import br.com.clientescrud.model.Usuario;
import br.com.clientescrud.repository.UsuarioRepository;
import br.com.clientescrud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody UsuarioRequestDto usuarioRequest){
        return usuarioService.criarUsuario(usuarioRequest);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> buscar(@PathVariable("idUsuario") Long idRecebido){
        return usuarioService.buscarUsuario(idRecebido);
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDto> atualizar(@RequestBody UsuarioRequestUpdateDto usuarioRecebido, @PathVariable("idUsuario") Long idUsuario){
        return usuarioService.atualizarUsuario(usuarioRecebido,idUsuario);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deletar(@PathVariable("idUsuario") Long id){
        return usuarioService.deletarUsuario(id);
    }

}
