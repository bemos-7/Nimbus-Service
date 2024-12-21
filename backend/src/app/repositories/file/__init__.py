from app.services.local_file_manager import LocalFileManager

from .v1.create_one import create_one
from .v1.delete_one import delete_one
from .v1.find_all import find_all
from .v1.find_one import find_one


class FileRepository:
    def __init__(self, local_file_manager: LocalFileManager):
        self.local_file_manager = local_file_manager

    create_one = create_one
    delete_one = delete_one
    find_all = find_all
    find_one = find_one
