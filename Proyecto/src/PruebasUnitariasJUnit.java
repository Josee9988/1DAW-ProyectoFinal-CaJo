import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import controller.controllerUsuarios.agregar_usuarios;
import dto.incidenciaDTO;
import dto.usuarioDTO;
import model.jdbcUsuarioDAO;

/**
 * @author Jose_Gracia_Berenguer, Carlos Robles
 * @version May 28, 2019
 * @param args Recibe los datos del programa
 */
class PruebasUnitariasJUnit {

	@Test
	void primeraPrueba() {
		incidenciaDTO incidenciaDTO = new incidenciaDTO(12, "Media");
		Assert.assertEquals(incidenciaDTO.getId(), 12);// fail[posicionarray], objeto.metodo(correcto)[posicion];
	}

	@Test
	void segundaPrueba() {
		incidenciaDTO incidenciaDTO = new incidenciaDTO();
		incidenciaDTO.setId(19);
		Assert.assertEquals(incidenciaDTO.getId(), 19);// fail[posicionarray], objeto.metodo(correcto)[posicion];
	}

	@Test
	void TerceraPrueba() {
		agregar_usuarios agregar_usuarios = new agregar_usuarios();
		dto.usuarioDTO usuarioDTO = new usuarioDTO();
		usuarioDTO.setRolS("Profesor");
		Assert.assertEquals(agregar_usuarios.traducirComboBox(usuarioDTO.getRolS()), 1);
	}

	@Test
	void cuartaPrueba() throws SQLException {
		jdbcUsuarioDAO jdbcUsuarioDAO = new jdbcUsuarioDAO();
		usuarioDTO usuarioDTO = new usuarioDTO();
		usuarioDTO.setUser("root");
		usuarioDTO.setPassword("a40d8205ed80c785d262e31d1b74f7c6");
		Assert.assertEquals(jdbcUsuarioDAO.comprobarExistencia(usuarioDTO), 4);
	}

	@Test
	void quintaPrueba() throws SQLException {
		jdbcUsuarioDAO jdbcUsuarioDAO = new jdbcUsuarioDAO();
		Assert.assertEquals(jdbcUsuarioDAO.devolverId("root"), 20);
	}

}
