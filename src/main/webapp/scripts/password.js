function toggle(id) {
	var passwordField = document.getElementById(id);
	
	if (passwordField.type === "password") {
		passwordField.type = "text";
	} else {
		passwordField.type = "password";
	}
}