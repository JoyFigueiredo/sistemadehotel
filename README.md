# 🏨 Sistema de Hotelaria

Sistema de gerenciamento de hotelaria desenvolvido com foco no controle de **hóspedes, quartos, reservas e hospedagens**, com possibilidade de expansão para pagamentos, relatórios e integrações externas.

---

## 📋 Funcionalidades

Um sistema básico de hotelaria deve contemplar:

* 👤 Hóspedes
* 🛏️ Quartos
* 📅 Reservas
* 🏨 Hospedagens
* 🔑 Check-in / Check-out
* 💰 Pagamentos
* 📊 Disponibilidade
* 🏷️ Tipos de quarto

---

## 🗺️ Visão geral do sistema

```text
                         SISTEMA DE HOTELARIA
                                  │
                 ┌────────────────┴────────────────┐
                 │                                 │
             MVP - V1.0                     Futuras versões
                 │                                 │
        ┌────────┼────────┐              ┌─────────┼─────────┐
        │        │        │              │         │         │
     Hóspede  Quarto   Reserva       Pagamentos  Relatórios  Usuários
        │        │        │
        └────────┼────────┘
                 │
          Hospedagem / Estadia
                 │
          ┌──────┴──────┐
          │             │
       Check-in     Check-out
          
                         │
                 Integrações futuras
                         │
              ┌──────────┼──────────┐
              │          │          │
            E-mail       PIX    Channel Manager
```

---

# 🚀 Versão 1.0 - MVP

A primeira versão do sistema terá como objetivo implementar o fluxo básico de uma hospedagem:

```text
Hóspede
   ↓
Reserva
   ↓
Quarto
   ↓
Check-in
   ↓
Hospedagem
   ↓
Check-out
```

## 1. 👤 Hóspedes

Responsável pelo cadastro e gerenciamento dos hóspedes.

### Entidade `Hospede`

| Variável       | Tipo     | Descrição                      |
| -------------- | -------- | ------------------------------ |
| `id`           | `UUID`   | Identificador único do hóspede |
| `firstName`    | `String` | Primeiro nome                  |
| `lastName`     | `String` | Sobrenome                      |
| `email`        | `String` | E-mail do hóspede              |
| `phone`        | `String` | Telefone                       |
| `address`      | `String` | Endereço                       |
| `documentoCPF` | `String` | CPF do hóspede                 |

### Operações

* Cadastrar hóspede
* Consultar hóspede
* Consultar todos os hóspedes
* Atualizar hóspede
* Remover/desativar hóspede
* Buscar hóspede por CPF
* Buscar hóspede por e-mail

### Estrutura inicial

```java
public class Hospede {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String documentoCPF;

}
```

---

## 2. 🛏️ Quartos

Responsável pelo cadastro e controle dos quartos disponíveis no hotel.

### Entidade `Quarto`

| Variável     | Tipo      | Descrição                                       |
| ------------ | --------- | ----------------------------------------------- |
| `id`         | `UUID`    | Identificador único do quarto                   |
| `numero`     | `String`  | Número ou identificação do quarto               |
| `tipo`       | `String`  | Tipo do quarto, como Standard, Luxo ou Suíte    |
| `capacidade` | `int`     | Quantidade máxima de hóspedes                   |
| `preco`      | `BigDecimal `  | Preço da diária                                 |
| `disponivel` | `boolean` | Indica se o quarto está disponível para reserva |

### Operações

* Cadastrar quarto
* Consultar quarto
* Consultar todos os quartos
* Atualizar quarto
* Remover quarto
* Alterar número do quarto
* Alterar tipo do quarto
* Alterar capacidade
* Alterar preço
* Verificar disponibilidade
* Alterar disponibilidade

### Estrutura inicial

```java
public class Quarto {

    private UUID id;
    private String numero;
    private String tipo;
    private int capacidade;
    private BigDecimal preco;
    private boolean disponivel;

}
```

---

## 3. 🏷️ Tipos de quarto

Responsável por definir as características e categorias dos quartos.

### Entidade `TipoQuarto`

| Variável      | Tipo         | Descrição              |
| ------------- | ------------ | ---------------------- |
| `id`          | `UUID`       | Identificador único    |
| `nome`        | `String`     | Nome do tipo de quarto |
| `descricao`   | `String`     | Descrição do quarto    |
| `capacidade`  | `Integer`    | Capacidade máxima      |
| `valorDiaria` | `BigDecimal` | Valor da diária        |

### Exemplos

```text
STANDARD
LUXO
FAMILIA
SUITE
```

### Operações

* Cadastrar tipo de quarto
* Consultar tipos de quarto
* Atualizar tipo de quarto
* Remover/desativar tipo de quarto
* Definir valor da diária
* Definir capacidade

---

## 4. 📅 Reservas

Responsável pelo agendamento de uma hospedagem.

### Entidade `Reserva`

| Variável      | Tipo            | Descrição                        |
| ------------- | --------------- | -------------------------------- |
| `id`          | `UUID`          | Identificador único da reserva   |
| `hospede`     | `Hospede`       | Hóspede responsável pela reserva |
| `quarto`      | `Quarto`        | Quarto reservado                 |
| `dataEntrada` | `LocalDate`     | Data prevista para entrada       |
| `dataSaida`   | `LocalDate`     | Data prevista para saída         |
| `status`      | `StatusReserva` | Situação da reserva              |

### Status da reserva

```text
PENDENTE
CONFIRMADA
CANCELADA
CONCLUIDA
```

### Operações

* Criar reserva
* Associar hóspede
* Associar quarto
* Informar data de entrada
* Informar data de saída
* Consultar reserva
* Consultar reservas
* Atualizar reserva
* Cancelar reserva
* Verificar disponibilidade do quarto

### Estrutura inicial

```java
public class Reserva {

    private UUID id;
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private StatusReserva status;

}
```

---

# 🏨 5. Hospedagem

A hospedagem representa a estadia efetiva do hóspede no hotel após o check-in.

### Entidade `Hospedagem`

| Variável       | Tipo               | Descrição                   |
| -------------- | ------------------ | --------------------------- |
| `id`           | `UUID`             | Identificador único         |
| `reserva`      | `Reserva`          | Reserva relacionada         |
| `dataCheckIn`  | `LocalDateTime`    | Data e horário do check-in  |
| `dataCheckOut` | `LocalDateTime`    | Data e horário do check-out |
| `status`       | `StatusHospedagem` | Situação da hospedagem      |

### Status

```text
ATIVA
FINALIZADA
CANCELADA
```

### Operações

* Realizar check-in
* Consultar hospedagem
* Atualizar hospedagem
* Realizar check-out
* Finalizar hospedagem

### Estrutura inicial

```java
public class Hospedagem {

    private UUID id;
    private Reserva reserva;
    private LocalDateTime dataCheckIn;
    private LocalDateTime dataCheckOut;
    private StatusHospedagem status;

}
```

---

# 💰 6. Pagamentos

> 🔜 Funcionalidade planejada para versões futuras.

Responsável pelo controle dos pagamentos relacionados às reservas e hospedagens.

### Entidade `Pagamento`

| Variável         | Tipo              | Descrição             |
| ---------------- | ----------------- | --------------------- |
| `id`             | `UUID`            | Identificador único   |
| `reserva`        | `Reserva`         | Reserva relacionada   |
| `valor`          | `BigDecimal`      | Valor do pagamento    |
| `dataPagamento`  | `LocalDateTime`   | Data do pagamento     |
| `formaPagamento` | `FormaPagamento`  | Forma utilizada       |
| `status`         | `StatusPagamento` | Situação do pagamento |

### Formas de pagamento

```text
PIX
CARTAO_CREDITO
CARTAO_DEBITO
DINHEIRO
```

---

# 📊 7. Disponibilidade

O sistema deverá permitir verificar a disponibilidade dos quartos considerando:

* Período da reserva
* Tipo de quarto
* Capacidade
* Situação atual do quarto
* Reservas existentes

Exemplo:

```text
Data de entrada: 10/10/2026
Data de saída:   15/10/2026

             ↓

Buscar quartos disponíveis
             ↓
      ┌──────┴──────┐
      │             │
   Quarto 101    Quarto 203
   Disponível    Disponível
```

---

# 👤 8. Usuários

> 🔜 Funcionalidade planejada para versões futuras.

Responsável pelo acesso ao sistema e controle de permissões.

Possíveis informações:

| Variável | Tipo            | Descrição           |
| -------- | --------------- | ------------------- |
| `id`     | `UUID`          | Identificador único |
| `nome`   | `String`        | Nome do usuário     |
| `email`  | `String`        | E-mail de acesso    |
| `senha`  | `String`        | Senha               |
| `perfil` | `PerfilUsuario` | Perfil de acesso    |

Possíveis perfis:

```text
ADMINISTRADOR
RECEPCAO
GERENTE
```

---

# 📈 9. Relatórios

> 🔜 Funcionalidade planejada para versões futuras.

Possíveis relatórios:

* Ocupação dos quartos
* Reservas por período
* Cancelamentos
* Check-ins realizados
* Check-outs realizados
* Faturamento
* Pagamentos
* Quartos disponíveis

---

# 🔗 Integrações futuras

O sistema poderá futuramente ser integrado com serviços externos.

```text
                 SISTEMA DE HOTELARIA
                         │
             ┌───────────┼───────────┐
             │           │           │
           E-mail       PIX     Channel Manager
             │           │           │
        Notificações  Pagamentos  Reservas externas
```

### 📧 E-mail

Possíveis utilizações:

* Confirmação de reserva
* Cancelamento
* Confirmação de pagamento
* Lembrete de check-in
* Confirmação de check-out

### 💳 PIX

Integração para:

* Geração de cobrança
* Confirmação de pagamento
* Atualização automática do status da reserva

### 🌐 Channel Manager

Possibilitar integração com plataformas externas de reservas, mantendo a disponibilidade dos quartos sincronizada.

---

# 🧩 Modelo inicial de entidades

```text
┌──────────────┐
│   HOSPEDE    │
├──────────────┤
│ id           │
│ firstName    │
│ lastName     │
│ email        │
│ phone        │
│ address      │
│ documentoCPF │
└──────┬───────┘
       │
       │ 1:N
       ▼
┌──────────────┐
│   RESERVA    │
├──────────────┤
│ id           │
│ hospede      │
│ quarto       │
│ dataEntrada  │
│ dataSaida    │
│ status       │
└──────┬───────┘
       │
       │ 1:1
       ▼
┌──────────────┐
│ HOSPEDAGEM   │
├──────────────┤
│ id           │
│ reserva      │
│ dataCheckIn  │
│ dataCheckOut │
│ status       │
└──────────────┘

┌──────────────┐
│    QUARTO    │
├──────────────┤
│ id           │
│ numero       │
│ tipo         │
│ capacidade   │
│ situacao     │
└──────┬───────┘
       │
       │ N:1
       ▼
┌──────────────┐
│ TIPO_QUARTO  │
├──────────────┤
│ id           │
│ nome         │
│ descricao    │
│ capacidade   │
│ valorDiaria  │
└──────────────┘
```

---

# 📌 Roadmap

## V1.0 - MVP

* [x] Entidade `Hospede`
* [ ] CRUD de hóspedes
* [ ] Entidade `TipoQuarto`
* [ ] Entidade `Quarto`
* [ ] CRUD de quartos
* [ ] Entidade `Reserva`
* [ ] CRUD de reservas
* [ ] Verificação de disponibilidade
* [ ] Entidade `Hospedagem`
* [ ] Check-in
* [ ] Check-out

## V2.0

* [ ] Pagamentos
* [ ] PIX
* [ ] Usuários
* [ ] Autenticação
* [ ] Controle de permissões
* [ ] Relatórios

## V3.0

* [ ] Integração com e-mail
* [ ] Channel Manager
* [ ] Sincronização de reservas externas
* [ ] Dashboard administrativo
* [ ] Relatórios avançados

---

# 🏗️ Estrutura conceitual

O sistema está sendo desenvolvido de forma incremental, começando pelas entidades e regras fundamentais do domínio:

```text
Hóspede
   │
   └── Reserva
          │
          ├── Quarto
          │      └── TipoQuarto
          │
          └── Hospedagem
                  │
                  └── Pagamento
```

A prioridade da **V1.0** é construir uma base sólida para o fluxo principal de uma hospedagem, deixando funcionalidades externas e administrativas para as próximas versões.
