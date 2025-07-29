# 1단계: 빌드 단계
FROM node:20-alpine AS build-stage

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# 2단계: 프로덕션 단계 (Nginx를 사용해서 정적 파일 서빙)
FROM nginx:stable-alpine AS production-stage

COPY --from=build-stage /app/dist /usr/share/nginx/html

# 기본 nginx 포트 80 오픈
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
