# Prometheus Workshop

1. Setup prometheus and grafana:
    ```bash
    docker-compose up -d
    ```

2. Build and run the Java app:
   ```shell
   ./gradlew clean build
   java -jar build/libs/prometheus-workshop-all.jar
   ```

3. On a separate terminal, connect to port 8080 to start inputting request duration values:
   ```shell
   nc localhost 8080
   24
   2
   5
   ...
   ```

4. View Grafana UI on the browser at [localhost:3000](http://localhost:3000)

### Architecture

![Architecture](https://github.com/krunalvora/prometheus-workshop/blob/images/images/prometheus-workshop.jpg)

