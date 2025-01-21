# foro_hub
Creación de API rest con temática de un foro para el challenge de ALURA 2024-2025

# Foro Hub - API BackEnd

Foro Hub es una aplicación de BackEnd desarrollada con **Spring Boot**, que proporciona funcionalidades para gestionar foros de discusión. Este proyecto incluye autenticación y autorización mediante JWT, manejo de usuarios, roles, tópicos, respuestas y más.

## 🚀 Funcionalidades Principales

- **Gestión de usuarios**: Registro, inicio de sesión y manejo de roles.
- **Autenticación y autorización**: Uso de **JWT** para proteger los endpoints.
- **Manejo de foros**:
  - Crear, listar, actualizar y eliminar tópicos.
  - Filtrar tópicos por curso, año y estado.
- **Gestión de respuestas**: Añadir y listar respuestas a tópicos.
- **Documentación de API**: Incluye **Swagger/OpenAPI** para facilitar la integración.
- **Seguridad**: Contraseñas cifradas con BCrypt y roles para controlar accesos.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.4**
  - Spring Security
  - Spring Data JPA
- **MySQL**: Base de datos relacional.
- **JWT**: Autenticación segura.
- **Swagger**: Documentación interactiva de la API.
- **Maven**: Gestión de dependencias.

---

## 📂 Endpoints Principales

### **Autenticación**
| Método | Endpoint         | Descripción                |
|--------|------------------|----------------------------|
| POST   | `/auth/login`    | Inicia sesión y genera un token JWT. |

### **Usuarios**
| Método | Endpoint         | Descripción                |
|--------|------------------|----------------------------|
| GET    | `/users`         | Lista todos los usuarios.  |
| GET    | `/users/{id}`    | Obtiene detalles de un usuario por ID. |

### **Tópicos**
| Método | Endpoint                     | Descripción                              |
|--------|------------------------------|------------------------------------------|
| POST   | `/topics`                    | Crea un nuevo tópico.                   |
| GET    | `/topics`                    | Lista todos los tópicos.                |
| GET    | `/topics/filter`             | Filtra tópicos por curso y año.         |
| GET    | `/topics/filter-by-status`   | Filtra tópicos por estado.              |
| PUT    | `/topics/{id}`               | Actualiza un tópico existente.          |
| DELETE | `/topics/{id}`               | Elimina un tópico por ID.               |

### **Respuestas**
| Método | Endpoint                 | Descripción                              |
|--------|--------------------------|------------------------------------------|
| POST   | `/topics/{id}/responses` | Añade una respuesta a un tópico.         |
| GET    | `/topics/{id}/responses` | Lista todas las respuestas de un tópico.|

---

## 🧪 Instalación y Configuración

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/Grimnir0219/foro_hub
