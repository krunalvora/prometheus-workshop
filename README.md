# Prometheus Workshop

1. Start Prometheus Workshop using:
    ```bash
    ./start-prometheus-workshop.sh
    ```

2. On a separate terminal, connect to port 8080 to start inputting request duration values:
   ```shell
   nc localhost 8080
   24
   2
   5
   ...
   ```

3. View prometheus UI on the browser at [localhost:9090](http://localhost:9090)

### Architecture

![Architecture](https://github.com/krunalvora/prometheus-workshop/blob/images/images/PrometheusWorkshop.png)

