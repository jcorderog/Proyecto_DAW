window.onload = iniciar;

function iniciar () {
	document.getElementById("registrar").addEventListener("click", validar, false);
		
}

function validarNombreApellidos() {
	let nombre = document.getElementById("name");
	let apellidos = document.getElementById("lastname");
	
	if(nombre.value === "" ) {
		Swal.fire ({
			icon: 'error',
			title: 'Warning!!',
			text:  'El campo nombre no debe estar vacio.'
		})
		return false;
	} else if (apellidos.value === "") {
		Swal.fire ({
			icon: 'error',
			title: 'Warning!!',
			text:  'El campo apellidos no debe estar vacio.'
		})
		return false;
	}
	return true;
}

function validarUsername () {
	let nombre = document.getElementById("username");
	if(nombre.value === "" ) {
		Swal.fire ({
			icon: 'error',
			title: 'Warning!!',
			text:  'El campo Username no debe estar vacio.'
		})
		return false;
	}
	return true;
}

function validarContrasena () {
	
	let password = document.getElementById("password");
	let rePass = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$/g
	
	if(rePass.test(password.value)) {
		return true;
	} else {
		Swal.fire ({
			icon: 'error',
			title: 'Warning!!',
			text:  'El campo password debe contener minusculas, mayusculas, signos y un número. Debe contener mínimo 6 caracteres y máximo 15'
		})
		return false;
	}
}

function validarCorreo () {
	let correo = document.getElementById("correo");
	let reCorreo = /^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$/g
	
	if(reCorreo.test(correo.value)) {
		return true;
	} else {
		Swal.fire ({
			icon: 'error',
			title: 'Warning!!',
			text:  'El campo Correo no es correcto'
		})
		return false;
	}
}

function validar (e) {
	if(validarNombreApellidos() && validarUsername() && validarContrasena() && validarCorreo() && confirm("Desea enviar estos datos")) {
		return true;
	} else {
		e.preventDefault();
		return false;
	}
}