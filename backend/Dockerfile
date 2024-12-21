FROM python:3.13

WORKDIR /code

RUN chmod 0700 /tmp

RUN pip install poetry

COPY ./pyproject.toml ./poetry.lock /code/
RUN poetry config virtualenvs.create false && poetry install --only main

COPY ./src /code/src
ENV PYTHONPATH=/code/src:/code/src/app

WORKDIR /code/src

CMD uvicorn app:app --host $NIMBUS__APP_HOST --port $NIMBUS__APP_PORT
