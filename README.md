
# TeamWorkspace

Веб-приложение для совместной работы над заметками внутри проектов.

## Функционал

- Каждый пользователь должен зарегистрироваться
- Пользователь может создавать свои проекты
- Пользователь может подключаться к чужим проектам по токену
- Участники проекта могут совместно редактировать заметки проекта

## Стек

### Backend
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Redis

### Frontend
- React
- TypeScript
- Vite
- Axios

### Infrastructure
- Docker
- Docker Compose

## Запуск проекта

### Установка

Убедитесь, что у вас установлены:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

Затем

```bash
git clone <repository-url>
cd TeamWorkspace
```

### Запуск

Собрать и запустить приложение:

```bash
docker compose -f deploy/docker-compose.yaml up -d --build
```

### Открыть приложение

- http://localhost:5173

## Project Structure

```text
TeamWorkspace/
├── backend/
├── frontend/
├── deploy/
└── README.md
```
