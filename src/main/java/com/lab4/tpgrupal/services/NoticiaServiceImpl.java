package com.lab4.tpgrupal.services;

import com.lab4.tpgrupal.entities.Noticia;
import com.lab4.tpgrupal.repositories.BaseRepository;
import com.lab4.tpgrupal.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class NoticiaServiceImpl extends BaseServiceImpl<Noticia, Integer> implements NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    public NoticiaServiceImpl(BaseRepository<Noticia, Integer> baseRepository) {
        super(baseRepository);
        this.noticiaRepository = noticiaRepository;
    }
    public List<Noticia> obtenerUltimas5Noticias() {
        return noticiaRepository.findTop5ByOrderByFechaPublicacionDesc();
    }
    public List<Noticia> buscarNoticiasPorPalabraClave(String palabraClave) {
        return noticiaRepository.findByTituloNoticiaContainingIgnoreCaseOrResumenNoticiaContainingIgnoreCaseOrContenidoHtmlContainingIgnoreCase(palabraClave, palabraClave, palabraClave);
    }

    public List<Noticia> buscarPorEmpresa(Integer idEmpresa) {
        return noticiaRepository.findByEmpresaIdOrderByFechaPublicacionDesc(idEmpresa);
    }

    public String guardarImagen(MultipartFile archivoImagen) throws IOException {
        // Generar un nombre único para la imagen
        String nombreUnico = UUID.randomUUID().toString();
        // Obtener la extensión del archivo original
        String extension = StringUtils.getFilenameExtension(archivoImagen.getOriginalFilename());
        // Generar el nombre completo de la imagen con extensión
        String nombreImagen = nombreUnico + "." + extension;
        // Guardar la imagen en el directorio de archivos del servidor
        Files.copy(archivoImagen.getInputStream(), Paths.get("src/main/resources/static/images/imagenesnoticias", nombreImagen), StandardCopyOption.REPLACE_EXISTING);
        // Devolver el nombre de la imagen para almacenarlo en la base de datos
        return nombreImagen;
    }
}
