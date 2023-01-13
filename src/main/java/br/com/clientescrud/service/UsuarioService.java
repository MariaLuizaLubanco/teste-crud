package br.com.clientescrud.service;

import br.com.clientescrud.controller.dto.UsuarioRequestDto;
import br.com.clientescrud.controller.dto.UsuarioRequestUpdateDto;
import br.com.clientescrud.controller.dto.UsuarioResponseDto;
import br.com.clientescrud.model.Usuario;
import br.com.clientescrud.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<?> criarUsuario(UsuarioRequestDto usuarioRequest) {
        Usuario usuario = modelMapper.map(usuarioRequest, Usuario.class);
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    public ResponseEntity<UsuarioResponseDto> buscarUsuario(Long id) {
        Optional<Usuario> opUsuario = usuarioRepository.findById(id);
        if(opUsuario.isPresent()){
            UsuarioResponseDto dto = modelMapper.map(opUsuario.get(), UsuarioResponseDto.class);
            return ResponseEntity.ok().body(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<?> deletarUsuario(Long id) {
        Optional<Usuario> opUsuario = usuarioRepository.findById(id);
        if(opUsuario.isPresent()){
            usuarioRepository.delete(opUsuario.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(UsuarioRequestUpdateDto usuarioRecebido, Long idUsuario) {
        Optional<Usuario> opUsuario = usuarioRepository.findById(idUsuario);
        if(opUsuario.isPresent()){
            Usuario usuarioParaAtualizar = opUsuario.get();
            usuarioParaAtualizar.setEmail(usuarioRecebido.getEmail());
            usuarioParaAtualizar.setSenha(usuarioRecebido.getSenha());
            usuarioRepository.save(usuarioParaAtualizar);
            UsuarioResponseDto usuarioResponseDto = modelMapper.map(usuarioParaAtualizar, UsuarioResponseDto.class);
            return ResponseEntity.ok().body(usuarioResponseDto);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDto> usuarioResponseDtos = new ArrayList<>();
        usuarios.forEach(usuario -> {
            UsuarioResponseDto usuarioDto = modelMapper.map(usuario,UsuarioResponseDto.class);
            usuarioResponseDtos.add(usuarioDto);
        });
        return ResponseEntity.ok().body(usuarioResponseDtos);
    }
}
