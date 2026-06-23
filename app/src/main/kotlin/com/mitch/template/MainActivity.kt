@Composable
fun RelojDespertadorScreen() {
    var horaActual by remember { mutableStateOf("") }
    var diaActual by remember { mutableStateOf("") }
    var mesActual by remember { mutableStateOf("") }

    // Actualización del reloj en segundo plano
    LaunchedEffect(Unit) {
        val formatoHora = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formatoDia = SimpleDateFormat("EEEE d", Locale.getDefault()) // Ej: "martes 23"
        val formatoMes = SimpleDateFormat("MMMM", Locale.getDefault())    // Ej: "junio"
        
        while (true) {
            val calendario = Calendar.getInstance()
            horaActual = formatoHora.format(calendario.time)
            diaActual = formatoDia.format(calendario.time)
            mesActual = formatoMes.format(calendario.time)
            delay(1000)
        }
    }

    // Contenedor principal horizontal (Para modo apaisado)
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Fondo negro puro para la noche
            .padding(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. PARTE IZQUIERDA: Hora (ocupa el 60% del ancho para darle prioridad)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.6f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = horaActual,
                color = Color(0xFF00FFCC), // Verde neón de alta visibilidad
                fontSize = 90.sp,          // Tamaño extra grande
                fontWeight = FontWeight.Bold
            )
        }

        // Línea vertical separadora sutil (opcional, estilo reloj minimalista)
        Spacer(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .width(1.dp)
                .background(Color.DarkGray)
        )

        // 2. PARTE DERECHA: Dividida en arriba (Día) y abajo (Mes)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f)
                .padding(start = 24.dp),
            verticalArrangement = Arrangement.SpaceEvenly, // Distribuye arriba y abajo equitativamente
            horizontalAlignment = Alignment.Start
        ) {
            // Derecha Arriba: Día de la semana y número
            Text(
                text = diaActual.replaceFirstChar { it.uppercase() },
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )

            // Derecha Abajo: Mes
            Text(
                text = mesActual.replaceFirstChar { it.uppercase() },
                color = Color.Gray,
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}
