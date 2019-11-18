# Dockerfile with tensorflow gpu support on python3, opencv3.3
FROM jovanhyw/docker-tensorflow-opencv:tf-1.14

# Python dependencies
RUN pip3 --no-cache-dir install \
  Cython \
  Flask \
  Pillow \
  flask-cors

WORKDIR /

# clone darkflow
RUN git clone https://github.com/thtrieu/darkflow.git \
  && cd darkflow \
  && pip install .
  
RUN pip install py_eureka_client

COPY . .

CMD ["flask", "run", "--host", "0.0.0.0"]