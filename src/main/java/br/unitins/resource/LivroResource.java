package br.unitins.resource;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unitins.model.Livro;

@Path("/livros")
public class LivroResource {

    @GET
    public List<Livro> getAll() {
        return Livro.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Livro getById(@PathParam("id") Long id) {
        return Livro.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Livro insert(Livro livro) {
        livro.persist();
        return livro;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Livro uptadte(@PathParam("id") Long id, Livro livro) {

        Livro entity = Livro.findById(id);

        entity.setTitulo(livro.getTitulo());
        entity.setAutor(livro.getAutor());
        entity.setIsbn(livro.getIsbn());
        entity.setDataPublicacao(livro.getDataPublicacao());
        entity.setGenero(livro.getGenero());
        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Livro delete(@PathParam("id") Long id) {
        Livro entity = Livro.findById(id);
        Livro.deleteById(id);
        return entity;
    }

}
