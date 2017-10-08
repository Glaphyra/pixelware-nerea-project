//Datos que carga la plantilla navbar dependiendo si la sesión está iniciada (muestra logout) o para iniciar (muestra login)
app.value("$login", {
	navbar: {
		link: "login",
		glyphtype: "in",
		text: "Iniciar sesión"
	}
});
app.value("$logout", {
	navbar: {
		link: "logout",
		glyphtype: "out",
		text: "Cerrar sesión"
	}
});