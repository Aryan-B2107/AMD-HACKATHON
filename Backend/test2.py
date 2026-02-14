from kubeflow.trainer import (
    TrainerClient,
    Initializer,
    HuggingFaceModelInitializer,
    BuiltinTrainer,
    TorchTuneConfig,
    TorchTuneInstructDataset,
    DataFormat,
    DataType,
)

TrainerClient().train(
    runtime=TrainerClient().get_runtime("torchtune-llama3.2-1b"),
    initializer=Initializer(
        model=HuggingFaceModelInitializer(
            storage_uri="hf://meta-llama/Llama-3.2-1B-Instruct",
            access_token="<YOUR_HF_TOKEN>",  # Replace with your Hugging Face token
        )
    ),
    trainer=BuiltinTrainer(
        config=TorchTuneConfig(
            dataset_preprocess_config=TorchTuneInstructDataset(
                source=DataFormat.PARQUET,
                split="train[:1000]",
                new_system_prompt="You are an AI assistant. ",
            ),
            epochs=10,
            dtype=DataType.BF16,
            resources_per_node={
                "memory": "200G",
                "gpu": 4,
            },
        )
    ),
)