# Prometheus Workshop

![](https://github.com/krunalvora/prometheus-workshop/blob/images/images/Prometheus%20Workshop.jpg)

### Run prometheus on docker using custom prometheus yaml

```bash
docker run \
-p 9090:9090 \
-v /path/to/prometheus.yml:/etc/prometheus/prometheus.yml \
prom/prometheus
```

### Start socket on port 8080
```bash
nc -l 8080
```

### Run Main.java from Intellij Idea

### Verify exposed prometheus metrics 
```shell
curl localhost:8081/metrics
```

### View prometheus UI on the browser at [localhost:9090](http://localhost:9090)
