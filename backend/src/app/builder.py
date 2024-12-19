import logging
import os

from app.api.http import v1
from app.core import local_file_manager_config
from app.repositories.providers import (
    provide_file_repository,
    provide_file_repository_stub,
    provide_user_repository,
    provide_user_repository_stub,
)
from app.services.providers import provide_local_file_manager_service
from fastapi import FastAPI


class ApplicationBuilder:
    def __init__(self, app: FastAPI) -> None:
        self.app = app
        self.logger = logging.getLogger(self.__class__.__name__)

    def _create_services(self):
        self.local_file_manager = lambda: provide_local_file_manager_service(local_file_manager_config.upload_dir)

    def _create_repositories(self) -> None:
        self.user_repository = lambda: provide_user_repository(local_file_manager=self.local_file_manager())
        self.file_repository = lambda: provide_file_repository(local_file_manager=self.local_file_manager())

    def _override_dependencies(self) -> None:
        self.app.dependency_overrides[provide_user_repository_stub] = self.user_repository
        self.app.dependency_overrides[provide_file_repository_stub] = self.file_repository

    def _add_routes(self) -> None:
        for router in v1.routes:
            self.app.include_router(router)

    @staticmethod
    def _configure_logging() -> None:
        logging.basicConfig(
            level=int(os.environ["LOGGING_LEVEL"]),
            format="%(levelname)s %(asctime)s %(filename)s:%(lineno)d %(message)s",
        )

    def build(self) -> "ApplicationBuilder":
        self._create_services()
        self._create_repositories()
        self._override_dependencies()
        self._add_routes()
        self._configure_logging()

        return self
