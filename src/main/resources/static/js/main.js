function gerarMatricula()
{
	var aleatorio = Math.floor(1000000000 + Math.random() * 8999999999);
	document.getElementById('matricula').value = aleatorio;
}