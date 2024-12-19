from app.repositories.file import FileRepository
from app.repositories.user import UserRepository
from app.services.local_file_manager import LocalFileManager


def provide_user_repository_stub():
    raise NotImplementedError


def provide_file_repository_stub():
    raise NotImplementedError


def provide_user_repository(local_file_manager: LocalFileManager) -> UserRepository:
    return UserRepository(local_file_manager=local_file_manager)


def provide_file_repository(local_file_manager: LocalFileManager) -> FileRepository:
    return FileRepository(local_file_manager=local_file_manager)
