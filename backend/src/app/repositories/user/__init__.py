from app.services.local_file_manager import LocalFileManager

from .v1.create_one import create_one


class UserRepository:
    def __init__(self, local_file_manager: LocalFileManager):
        self.local_file_manager = local_file_manager

    create_one = create_one
