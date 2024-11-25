import { defineConfig } from "vite";
import react from "@vitejs/plugin-react-swc";
import dotenv from "dotenv";
dotenv.config();

const apiUrl = process.env.VITE_API_URL;
const apiKey = process.env.VITE_API_KEY;
const port = parseInt(process.env.VITE_PORT || "3000", 10); 
export default defineConfig({
  plugins: [react()],
  server: {
    host: true,
    port,
  },
  preview: {
    port,
  },

  define: {
    __API_URL__: JSON.stringify(apiUrl),
    __API_KEY__: JSON.stringify(apiKey),
  },
  build: {
    sourcemap: process.env.VITE_SOURCEMAP === "true",
    outDir: process.env.VITE_OUTPUT_DIR || "dist",
  },
});
