from app.services.local_file_manager import LocalFileManager


def provide_local_file_manager_service(upload_dir_url: str) -> LocalFileManager:
    return LocalFileManager(upload_dir=upload_dir_url)
