package br.com.quatroquatros.gestaoDeResiduos.controller;


import br.com.quatroquatros.gestaoDeResiduos.dto.BaseResponseDto;
import br.com.quatroquatros.gestaoDeResiduos.dto.estado.EstadoCadastroDto;
import br.com.quatroquatros.gestaoDeResiduos.dto.estado.EstadoExibicaoDto;
import br.com.quatroquatros.gestaoDeResiduos.dto.estado.EstadoUpdateDto;
import br.com.quatroquatros.gestaoDeResiduos.exception.ModelNotFoundException;
import br.com.quatroquatros.gestaoDeResiduos.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {


    private final EstadoService service;

    @Autowired
    public EstadoController(EstadoService service) {
        this.service = service;
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<Page<EstadoExibicaoDto>> listarEstados(Pageable paginacao){
        return new BaseResponseDto<>(
                "busca de estados feita com sucesso!",
                service.listarTodos(paginacao)
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<EstadoExibicaoDto> buscarEstadoPorId(@PathVariable Long id){
        try{
            return new BaseResponseDto<>(
                    "busca de estado feita com sucesso!",
                    service.buscarPorId(id)
            );
        }catch (ModelNotFoundException e){
            throw new ModelNotFoundException("estado não encontrado");
        }

    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponseDto<EstadoExibicaoDto> gravar(@RequestBody @Valid EstadoCadastroDto estadoDados){
        return new BaseResponseDto<>(
                "estado cadastrado com sucesso!",
                service.gravar(estadoDados)
        );
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<EstadoExibicaoDto> atualizar(@PathVariable Long id, @RequestBody EstadoUpdateDto estadoDados){
        try {
            return new BaseResponseDto<>(
                    "estado atualizado com sucesso!",
                    service.atualizar(id, estadoDados)
            );
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("estado não encontrado");

        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponseDto<Object> excluir(@PathVariable Long id){
        try {
            service.excluir(id);
            return new BaseResponseDto<>("estado excluido com sucesso");
        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("estado não encontrado");

        }
    }

    @GetMapping("/uf/{uf}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponseDto<EstadoExibicaoDto> buscarEstadoPorUF(@PathVariable String uf){
        try {
                return service.buscarEstadoPorUF(uf);

        } catch (ModelNotFoundException e) {
            throw new ModelNotFoundException("estado não encontrado");

        }


    }





}