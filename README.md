# 🌍 Sistema Distribuído de Relógio Mundial

---

# 📌 Resumo do Projeto

Este projeto tem como objetivo desenvolver a comunicação entre processos utilizando **Sockets em Java**, explorando diferentes modelos de comunicação em sistemas distribuídos:

* **UDP (User Datagram Protocol)** – comunicação sem conexão
* **TCP (Transmission Control Protocol)** – comunicação orientada à conexão
* **Servidor concorrente (Multithread)** – atendimento simultâneo de múltiplos clientes

A aplicação permite que um cliente envie uma região geográfica (ex: `America/Sao_Paulo`) e receba como resposta a **data e hora atual formatada** dessa localidade.

---

# 📁 Organização de Pastas

```id="2o3l5g"
udp-clock/                → Versão 1 (UDP)
tcp-clock-simple/         → Versão 2 (TCP simples)
tcp-clock-multithread/    → Versão 3 (TCP concorrente)
```

---

# ▶️ Instruções de Execução

## 🔹 Versão 1: UDP (Não orientado à conexão)

📂 Pasta: `udp-clock`

---

## ▶️ Execução

1. Abra a pasta `udp-clock` na IDE
2. Clique com botão direito em `UDPServer.java` → **Run**
3. Depois execute `UDPClient.java`
4. Digite a região solicitada

---

## ⚠️ Observação

* O cliente possui timeout de 5 segundos
* Caso não haja resposta:

```
Servidor ocupado ou offline
```

---

# 🔹 Versão 2: TCP Simples (Single Thread)

📂 Pasta: `tcp-clock-simple`

---

## ▶️ Execução 

1. Abra a pasta `tcp-clock-simple`
2. Execute `TCPServer.java`
3. Execute `TCPClient.java`
4. Digite a região desejada

---

## ⚠️ Característica

* O servidor atende **um cliente por vez**
* Outros clientes ficam aguardando na fila

---

# 🔹 Versão 3: TCP Multithread (Concorrente)

📂 Pasta: `tcp-clock-multithread`

---

## ▶️ Execução via TERMINAL

### 1. Compilar

```
cd tcp-clock-multithread
javac *.java
```

---

## ⚠️ IMPORTANTE (Uso de package)

Se os arquivos possuem:

```
package tcp_clock_multithread;
```

Execute a partir da pasta acima:

```
cd ..
```

---

### ▶️ Executar Servidor

```
java tcp_clock_multithread.TCPServerMulti
```

### ▶️ Executar Cliente

```
java tcp_clock_multithread.TCPClient
```

---

## ▶️ Execução via IDE

1. Abra `tcp-clock-multithread`
2. Execute `TCPServerMulti.java`
3. Execute `TCPClient.java` em múltiplas abas
4. Observe o log das threads no servidor

---

## 🔥 Teste de Concorrência

Execute vários clientes ao mesmo tempo.

Saída esperada:

```
Thread Thread-0 atendendo cliente /127.0.0.1:XXXXX
Thread Thread-1 atendendo cliente /127.0.0.1:XXXXX
```

✔ Demonstra atendimento simultâneo.

---

# 🌎 Regiões de Teste

```
America/Sao_Paulo
Europe/London
Asia/Tokyo
America/New_York
```

---

# 📊 Análise Técnica

## 🔹 TCP Simples

* Atendimento sequencial (um cliente por vez)
* Clientes ficam em fila de espera
* Menor desempenho sob múltiplas conexões

## 🔹 TCP Multithread

* Atendimento concorrente (várias threads)
* Vários clientes simultaneamente
* Melhor desempenho e escalabilidade

## 📌 Diferença observada

Quando múltiplos clientes se conectam:

* **TCP simples** → aumento do tempo de resposta
* **TCP multithread** → respostas rápidas e paralelas

---

# ✅ Boas Práticas Aplicadas

* Uso de **Javadoc** em todas as classes
* Organização modular por versão
* Tratamento de exceções
* Separação clara entre cliente e servidor
* Uso de API moderna (`ZonedDateTime`)

---

# 📌 Conclusão

O projeto demonstra, na prática, a importância da escolha do protocolo e do modelo de concorrência em sistemas distribuídos, evidenciando como o uso de múltiplas threads melhora significativamente a performance e a escalabilidade de servidores.

---
