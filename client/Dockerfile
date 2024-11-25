# Etapa de construcción
FROM node:18 AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar package.json y package-lock.json para la instalación de dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar todo el código fuente al contenedor
COPY . .

# Ejecutar el build de Vite
RUN npm run build

# Etapa de producción
#FROM nginx:alpine

# Copiar los archivos de la carpeta build a la carpeta del servidor nginx
#COPY --from=build /app/dist /usr/share/nginx/html

# Exponer el puerto 8080
EXPOSE 8080

# Iniciar nginx
CMD ["npm", "run", "dev"]
